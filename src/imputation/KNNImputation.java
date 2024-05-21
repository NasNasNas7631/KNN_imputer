import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.*;

public class KNNImputation {

    // Function to calculate Euclidean distance between two points
    public static double euclideanDistance(double[] point1, double[] point2) {
        double sum = 0.0;
        for (int i = 0; i < point1.length; i++) {
            sum += Math.pow(point1[i] - point2[i], 2);
        }
        return Math.sqrt(sum);
    }

    // Function to find all indices of NaN values in the dataset
    public static List<int[]> findNaNIndices(double[][] data) {
        List<int[]> nanIndices = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (Double.isNaN(data[i][j])) {
                    nanIndices.add(new int[]{i, j});
                }
            }
        }
        return nanIndices;
    }

    // Function to impute missing values using KNN
    public static void knnImputation(double[][] data, int k) {
        List<int[]> nanIndices = findNaNIndices(data);

        // Continue iterating until all NaN values are replaced
        while (!nanIndices.isEmpty()) {
            for (int[] nanIndex : nanIndices) {
                int rowIndex = nanIndex[0];
                int colIndex = nanIndex[1];

                Map<Double, Double> distances = new TreeMap<>();
                for (int j = 0; j < data.length; j++) {
                    if (rowIndex != j && !Double.isNaN(data[j][colIndex])) {
                        double dist = euclideanDistance(data[rowIndex], data[j]);
                        distances.put(dist, data[j][colIndex]);
                    }
                }

                // Select the nearest neighbors
                double sum = 0.0;
                int count = 0;
                for (Map.Entry<Double, Double> entry : distances.entrySet()) {
                    if (count == k) break;
                    sum += entry.getValue();
                    count++;
                }

                // Impute the missing value
                double imputedValue = sum / k;
                data[rowIndex][colIndex] = imputedValue;
            }

            // Find the indices of remaining NaN values
            nanIndices = findNaNIndices(data);
        }
    }

    public static void main(String[] args) {
        // Example dataset with missing values
        double[][] data = importDataFromExcel("src/imputation/UVWN.xlsx");

        int k = 2; // Number of neighbors to consider for imputation

        // Perform KNN imputation
        knnImputation(data, k);

        // Print the imputed dataset
        System.out.println("Imputed Data:");
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + "\t");
            }
            System.out.println();
        }

        // Save the imputed data to a new Excel file
        exportDataToExcel(data, "src/imputation/ImputedData.xlsx");
    }

    // Function to import data from Excel file
    public static double[][] importDataFromExcel(String fileName) {
        try {
            FileInputStream file = new FileInputStream(new File(fileName));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();

            double[][] data = new double[rows][cols];

            for (int i = 0; i < rows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 1; j < cols; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        if (cell.getCellType() == CellType.NUMERIC) {
                            data[i][j] = cell.getNumericCellValue();
                        } else {
                            // Handle other types if needed
                            data[i][j] = Double.NaN;
                        }
                    }
                }
            }

            workbook.close();
            file.close();

            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Function to export data to Excel file
    public static void exportDataToExcel(double[][] data, String fileName) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Imputed Data");

            for (int i = 0; i < data.length; i++) {
                Row row = sheet.createRow(i);
                for (int j = 0; j < data[i].length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(data[i][j]);
                }
            }

            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            workbook.close();
            fileOut.close();
            System.out.println("Imputed data saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

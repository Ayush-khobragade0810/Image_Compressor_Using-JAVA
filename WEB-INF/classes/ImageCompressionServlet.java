import java.io.*;
import java.util.*;
import java.awt.image.*;
import java.awt.Graphics2D;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/ImageCompressionServlet")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 10, // 10 MB
    maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class ImageCompressionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Get the uploaded file part
            Part filePart = request.getPart("imageFile");
            if (filePart == null || filePart.getSize() == 0) {
                out.println("<p style='color:red;'>No file uploaded. Please select a file and try again.</p>");
                return;
            }

            String fileName = filePart.getSubmittedFileName();

            // Validate the file extension
            String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
            List<String> validExtensions = Arrays.asList("jpg", "jpeg", "png", "bmp", "gif");

            if (!validExtensions.contains(extension)) {
                out.println("<p style='color:red;'>Invalid file type. Please upload a valid image file.</p>");
                return;
            }

            // Save the uploaded file
            String uploadPath = getServletContext().getRealPath("") + "uploads/";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            File uploadedFile = new File(uploadPath + fileName);
            filePart.write(uploadedFile.getAbsolutePath());

            // Read and validate the image
            BufferedImage image = ImageIO.read(uploadedFile);
            if (image == null) {
                out.println("<p style='color:red;'>The file is not a valid image. Please upload a valid image file.</p>");
                return;
            }

            // Convert to RGB color space
            BufferedImage rgbImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = rgbImage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();

            // Compress the image
            File compressedImageFile = new File(uploadPath + "compressed_" + fileName);
            OutputStream os = new FileOutputStream(compressedImageFile);
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
            if (!writers.hasNext()) {
                out.println("<p style='color:red;'>No suitable writers found for JPG format.</p>");
                return;
            }
            ImageWriter writer = writers.next();

            ImageOutputStream ios = ImageIO.createImageOutputStream(os);
            writer.setOutput(ios);
            // Compression Logic
            ImageWriteParam param = writer.getDefaultWriteParam();
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); 
            try {
                // Get the quality parameter
                float quality = 0.7f; // Default quality
                String qualityParam = request.getParameter("quality");
                if (qualityParam != null) {
                    quality = Float.parseFloat(qualityParam);
                    if (quality < 0.0f || quality > 1.0f) {
                        throw new IllegalArgumentException("Quality out of bounds!");
                    }
                }
                param.setCompressionQuality(quality);
            
                // Continue with the compression process...
            
            } catch (IllegalArgumentException e) {
                out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
            } catch (Exception e) {
                out.println("<p style='color:red;'>An unexpected error occurred: " + e.getMessage() + "</p>");
            }
   

            writer.write(null, new IIOImage(rgbImage, null, null), param);

            os.close();
            ios.close();
            writer.dispose();

            out.println("<p style='color:green;'>Image compression completed successfully.</p>");
            out.println("<p>Download compressed image: <a href='uploads/compressed_" + fileName + "'>Click here</a></p>");

        } catch (Exception e) {
            out.println("<p style='color:red;'>An error occurred: " + e.getMessage() + "</p>");
        }
    }
}

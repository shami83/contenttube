package util;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Mar 4, 2010
 * Time: 9:42:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageServlet extends HttpServlet {

      public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Get the absolute path of the image
        ServletContext sc = getServletContext();
        String filename = "D:\\Uploads\\"+req.getParameter("filename");

        // Get the MIME type of the image
        String mimeType = sc.getMimeType(filename);
        if (mimeType == null) {
            sc.log("Could not get MIME type of "+filename);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        // Set content type
        resp.setContentType(mimeType);

        // Set content size
        File file = new File(filename);
        resp.setContentLength((int)file.length());

        // Open the file and output streams
        FileInputStream in = new FileInputStream(file);
        OutputStream out = resp.getOutputStream();

        // Copy the contents of the file to the output stream
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        in.close();
        out.close();
    }

      public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
      {
          doGet(req,resp);
      }

}

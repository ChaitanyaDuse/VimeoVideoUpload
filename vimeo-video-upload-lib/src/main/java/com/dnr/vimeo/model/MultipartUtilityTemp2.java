package com.dnr.vimeo.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class MultipartUtilityTemp2 {

    private static final String LINE_FEED = "\r\n";
    private HttpURLConnection httpConn;
    private String charset;
    private OutputStream outputStream;
    private PrintWriter writer;
    private int MAX_BUFFER_SIZE = 4096;

    /**
     * This constructor initializes a new HTTP POST request with content type
     * is set to multipart/form-data
     *
     * @param requestURL
     * @param charset
     * @throws IOException
     */
    public MultipartUtilityTemp2(String requestURL, File uploadFile, String charset)
            throws IOException {
        this.charset = charset;

        URL url = new URL(requestURL);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("PUT");
        httpConn.setChunkedStreamingMode(MAX_BUFFER_SIZE);
        httpConn.setUseCaches(false);
        httpConn.setDoOutput(true);    // indicates POST method
        httpConn.setDoInput(true);
        httpConn.setRequestProperty("Connection", "Keep-Alive");
        httpConn.setConnectTimeout(300);
        httpConn.setReadTimeout(300);
        httpConn.setRequestProperty("Content-Type", "video/mp4");
        httpConn.setRequestProperty("Accept", "application/vnd.vimeo.*+json;version=3.2");
        httpConn.setRequestProperty("Content-Length", uploadFile.length() + "");
        outputStream = httpConn.getOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                true);

        FileInputStream inputStream = new FileInputStream(uploadFile);
        byte[] buffer = new byte[MAX_BUFFER_SIZE];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        inputStream.close();


        writer.flush();
    }


    public List<String> finish() throws IOException {
        List<String> response = new ArrayList<String>();


        writer.close();

        // checks server's status code first
        int status = httpConn.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpConn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.add(line);
            }
            reader.close();
            httpConn.disconnect();
        } else {
            throw new IOException("Server returned non-OK status: " + status);
        }

        return response;
    }
}
package org.jenkinsci.plugins.lighthouse;

import java.io.PrintStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

public class LightController {
    private final PrintStream logger;
    private LighthouseBridge lighthouseBridge;

    /**
     * Connect with a hue bridge.
     *
     * @param descriptor The descriptor for this application
     * @param logger     logger stream
     */
    public LightController(LightNotifier.DescriptorImpl descriptor, PrintStream logger) {
        this.logger = logger;
        this.lighthouseBridge = new LighthouseBridge(descriptor.getLighthouseUrl(), descriptor.getLighthouseOp());

    }

    /**
     * Sets the color of a light.
     *
     * @param light light object that should be manipulated
     * @param logName The name to use for logging this color state change
     * @param hue The hue of the desired color
     * @return true if color update was successful, otherwise false
     */
    public boolean setColor(String lighthouseOp, String logName, String color) {

        if (null == this.lighthouseBridge || null == lighthouseOp)
            return false;

        try {
            this.lighthouseBridge.setLightState(color);
            this.logInfo("set color of light " + lighthouseOp + " (" + lighthouseBridge + ")" + " to " + logName + " (" + color + ")");
        } catch (Exception e) {
            this.logError(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean setPulseColor(String lighthouseOp, String logName, String color) {

        if (null == this.lighthouseBridge || null == lighthouseOp)
            return false;

        try {
            this.lighthouseBridge.setLightState(color);
            this.logInfo("set pulse color of light " + lighthouseOp + " (" +lighthouseBridge + ")" + " to " + logName + " (" + color + ")");
        } catch (Exception e) {
            this.logError(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean setPulseBreathe(String lighthouseOp, String logName, String color) {

        if (null == this.lighthouseBridge || null == lighthouseOp)
            return false;

        try {
            this.lighthouseBridge.setLightState(color);
            this.logInfo("set breathe color of light " + lighthouseOp + " (" + lighthouseBridge + ")" + " to " + logName + " (" + color + ")");
        } catch (Exception e) {
            this.logError(e.getMessage());
            return false;
        }

        return true;
    }

    private void logError(String msg) {
        this.logger.println("hue-light-error: " + msg);
    }

    private void logInfo(String msg) {
        this.logger.println("hue-light: " + msg);
    }
    
    private class LighthouseBridge {
    	//Pattern: http://<server>:5000
    	String url;
    	String op;
    	HttpClient client = new HttpClient();
    	
		public LighthouseBridge(String url, String op) {
			this.url = url;
			this.op = op;
		}
    	
		public void setLightState(String color) throws Exception {
			
			PostMethod post = new PostMethod(url+"/set");
			post.addParameter("op", op);
			post.addParameter("color", color);
			
			int statusCode = client.executeMethod(post);
			
			System.out.println("Status Code = "+statusCode);
            System.out.println("QueryString>>> "+post.getQueryString());
            System.out.println("Status Text>>>"
                  +HttpStatus.getStatusText(statusCode));

			
		}
    	
    }
}

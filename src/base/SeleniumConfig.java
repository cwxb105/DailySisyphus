package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

public class SeleniumConfig {

	public static final String CONFIG_SELENIUM_SERVER_URL = "selenium.server.url";

	public static final String CONFIG_TARGET_BASE_URL = "target.base.url";

	private static ResourceBundle RESOURCE_BUNDLE;

	private static Properties props = new Properties();

	public static void init(String bundleName) {
		bundleName = StringUtils.removeEnd(bundleName, ".properties");
		RESOURCE_BUNDLE = ResourceBundle.getBundle(bundleName);
	}

	public static void initWithPath(String path) {
		props = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(path));
			props.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private SeleniumConfig() {
	}

	public static String getValue(String key) {
		try {
			if (null == RESOURCE_BUNDLE) {
				return props.getProperty(key);
			} else {
				return RESOURCE_BUNDLE.getString(key);
			}
		} catch (MissingResourceException e) {
			return null;
		}
	}

}

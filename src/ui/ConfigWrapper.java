package ui;

import config.Configuration;

/**
 * Wraps a Configuration object with gui goodness to allow Configuration to be immutable
 * @author Malfeasant
 */
public class ConfigWrapper {
	private String name;
	private Configuration config;
	
	public ConfigWrapper() {
		this("", Configuration.getDefault());
	}
	
	public ConfigWrapper(String name, Configuration config) {
		this.name = name;
		this.config = config;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Configuration getConfig() {
		return config;
	}
	public void setConfig(Configuration config) {
		this.config = config;
	}
}

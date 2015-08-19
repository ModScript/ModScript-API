package tk.coaster3000.modscript.addon;

import tk.coaster3000.modscript.ModScript;

public interface Addon {
	/**
	 * Retrieves the name of the addon.
	 * @return String
	 */
	String getName();

	/**
	 * Retrieves the addon's description.
	 * @return String
	 */
	String getDescription();

	/**
	 * Retrieves the addon's version.
	 * @return String
	 */
	String getVersion();

	/**
	 * Retrieves the supported plugin's version. <br/>
	 * It is used to make sure to inform server owner's that the addon is, or isn't supported by the current version od ModScript.
	 * @return String
	 */
	String getSupportedVersion();

	/**
	 * Retrieves an array of authors of this addon.
	 * @return String Array
	 */
	String[] getAuthors();

	/**
	 * Returns whether or not the addon is enabled.
	 * @return boolean
	 */
	boolean isEnabled();

	/**
	 * Called on initialization of the class.
	 */
	void init(ModScript modScript);

	/**
	 * Called when the addon is enabled.
	 */
	void enable();

	/**
	 * Called when the addon is disabled.
	 */
	void disable();

	/**
	 * Retrieve the addon getPriority.
	 *
	 * <p>
	 *     Higher values get executed farther down the line.
	 * </p>
	 * @return int
	 */
	int getPriority();
}

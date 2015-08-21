package tk.coaster3000.modscript.addon;

import tk.coaster3000.modscript.ModScript;

public interface Addon {

	/**
	 * Gets the addon's name.
	 *
	 * @return the name
	 */
	String getName();

	/**
	 * Gets the addon's description.
	 *
	 * @return the description
	 */
	String getDescription();

	/**
	 * Gets the addon's version.
	 *
	 * @return the version
	 */
	String getVersion();

	/**
	 * Gets supported version.
	 *
	 * @return the supported version
	 */
	String getSupportedVersion();

	/**
	 * Get the addon's authors.
	 *
	 * @return the string [ ]
	 */
	String[] getAuthors();

	/**
	 * Gets whether or not the addon is enabled.
	 *
	 * @return the boolean
	 */
	boolean isEnabled();

	/**
	 * Called by ModScript when initializing the addon.
	 *
	 * @param modScript the mod script
	 */
	void init(ModScript modScript);

	/**
	 * Called by ModScript when enabling the addon.
	 */
	void enable();

	/**
	 * Called by ModScript when disabling the addon.
	 */
	void disable();

	/**
	 * Gets the addon's priority.
	 *
	 * @return the priority
	 */
	int getPriority();
}

package tk.coaster3000.modscript.addon;

import tk.coaster3000.modscript.ModScript;
import tk.coaster3000.modscript.util.IncompatibleVersionException;
import tk.coaster3000.modscript.util.VersionUtil;

public abstract class JavaAddon implements Addon {
	private ModScript modScript;
	private boolean isEnabled = false;
	private boolean isInitialized = false;

	public JavaAddon() {}

	abstract AddonMeta getMeta();

	public final String getName() {
		return getMeta().getName();
	}

	public final String getDescription() {
		return getMeta().getDescription();
	}

	public final String getVersion() {
		return getMeta().getVersion();
	}

	public final String getSupportedVersion() {
		return getMeta().getSupportedVersion();
	}

	public final String[] getAuthors() {
		return getMeta().getAuthors();
	}

	protected final ModScript getModScript() {
		return this.modScript;
	}

	public final void init(ModScript modScript) {
		this.modScript = modScript;
		try {
			VersionUtil.Version version = VersionUtil.parse(getSupportedVersion());
			if (!version.isCompatibleWith(modScript.getVersion())) throw new IncompatibleVersionException("Addon version is not compatible with current ModScript version.", new IllegalArgumentException());
			this.onInit(modScript);
			this.isInitialized = true;
		} catch (Exception e) {
			modScript.reportAddon(new AddonInitializationException(this, e));
		}
	}

	@Override
	public final int getPriority() {
		return getMeta().getPriority();
	}

	@Override
	public final void disable() {
		try {
			onDisable();
		} catch (Exception e) {
			this.getModScript().reportAddon(new AddonDisableException(this, e));
		}
	}

	@Override
	public final void enable() {
		try {
			onEnable();
		} catch (Exception e) {
			this.getModScript().reportAddon(new AddonEnableException(this, e));
		}
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}

	@Override
	public boolean isInitialized() {
		return this.isInitialized;
	}

	abstract void onInit(ModScript modScript);
	abstract void onEnable();
	abstract void onDisable();

	public ModScript getModscript() {
		return this.modScript;
	}
}

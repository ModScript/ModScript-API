package tk.coaster3000.modscript.addon;

import tk.coaster3000.modscript.ModScript;

public class DummyJavaAddon extends JavaAddon {

	private final AddonMeta meta;

	public DummyJavaAddon(AddonMeta metaData) {
		this.meta = metaData;
	}

	@Override
	final AddonMeta getMeta() {
		return this.meta;
	}

	@Override
	public void onInit(ModScript modScript) {

	}

	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {

	}
}

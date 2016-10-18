package tk.coaster3000.modscript.addon;

public class DummyAddonMeta implements AddonMeta {

	private final String name;
	private final String description;
	private final String version;
	private final String supportedVersion;
	private final String[] authors;
	private final int priority;

	public DummyAddonMeta(String name, String description, String version, String supportedVersion, String[] authors, int priority){
		this.name = name;
		this.description = description;
		this.version = version;
		this.supportedVersion = supportedVersion;
		this.authors = authors;
		this.priority = priority;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String getVersion() {
		return this.version;
	}

	@Override
	public String getSupportedVersion() {
		return this.supportedVersion;
	}

	@Override
	public String[] getAuthors() {
		return this.authors;
	}

	@Override
	public int getPriority() {
		return this.priority;
	}
}

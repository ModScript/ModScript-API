package tk.coaster3000.modscript.logging;

public enum LogLevel {
	CRITICAL(1000), SEVERE_ERROR(900), ERROR(800),
	WARNING(600), INFO(400), DEBUG(200);

	private final int numericValue;

	LogLevel(int value) {
		this.numericValue = value;
	}

	public int getNumericValue() {
		return numericValue;
	}

	public static LogLevel getLevel(int value) {
		for (LogLevel level : LogLevel.values())
			if (level.getNumericValue() < value) return level;
		return null;
	}

	public boolean inRange(LogLevel level) {
		return inRange(level.getNumericValue());
	}

	public boolean inRange(int level) {
		return getNumericValue() < level;
	}
}

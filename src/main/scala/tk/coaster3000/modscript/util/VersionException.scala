package tk.coaster3000.modscript.util
class VersionException(val message: String, val cause: Throwable) extends RuntimeException(message, cause) {

}

class InvalidVersionException(override val message: String, override val cause: Throwable) extends VersionException(message, cause) {

}

class IncompatibleVersionException(override val message: String, override val cause: Throwable) extends VersionException(message, cause) {

}


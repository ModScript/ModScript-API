package tk.coaster3000.modscript.util

class InvalidVersionException(val message: String, val cause: Throwable) extends RuntimeException(message, cause) {

}

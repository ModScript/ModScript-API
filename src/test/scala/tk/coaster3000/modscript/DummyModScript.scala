package tk.coaster3000.modscript
import tk.coaster3000.modscript.addon.{Addon, WrappedAddonException}
import tk.coaster3000.modscript.logging.LogLevel

class DummyModScript extends ModScript {
	var rethrowMode = false
	override def reportAddon(wrappedAddonException: WrappedAddonException): Unit = if (rethrowMode) throw wrappedAddonException else log(wrappedAddonException.addon, LogLevel.ERROR, wrappedAddonException.message)

	override def log(addon: Addon, logLevel: LogLevel, msg: String): Unit = println("[" + addon.getName + ":" + logLevel.name + "] " + msg)

	override def getVersionString(): String = "0.1.0"
}

package tk.coaster3000.modscript.addon

class WrappedAddonException(val addon: Addon, val message: String, val cause: Throwable) extends RuntimeException(message, cause) {
	def this(addon: Addon, cause: Throwable) {
		this(addon, addon.getName + " had an exception occur during runtime.", cause)
	}
}

class AddonEnableException(override val addon: Addon, override val message: String, override val cause: Throwable) extends WrappedAddonException(addon, message, cause) {
	def this(addon: Addon, cause: Throwable) {
		this(addon, addon.getName + " had an exception occur while being enabled.", cause)
	}
}

class AddonDisableException(override val addon: Addon, override val message: String, override val cause: Throwable) extends WrappedAddonException(addon, message, cause) {
	def this(addon: Addon, cause: Throwable) {
		this(addon, addon.getName + " had an exception occur while being disabled.", cause)
	}
}

class AddonInitializationException(override val addon: Addon, override val message: String, override val cause: Throwable) extends WrappedAddonException(addon, message, cause) {
	def this(addon: Addon, cause: Throwable) {
		this(addon, addon.getName + " had an exception occur while being initialized", cause)
	}
}

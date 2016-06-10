package tk.coaster3000.modscript.util

import tk.coaster3000.modscript.addon.Addon

class AddonAlreadyExistsException(msg: String) extends RuntimeException(msg) {
	def this(addon:Addon) {
		this(addon.getName())
	}
}

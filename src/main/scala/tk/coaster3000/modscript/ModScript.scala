package tk.coaster3000.modscript

import tk.coaster3000.modscript.addon.{Addon, AddonDisableException, AddonEnableException, WrappedAddonException}
import tk.coaster3000.modscript.logging.LogLevel
import tk.coaster3000.modscript.util.{AddonAlreadyExistsException, VersionUtil}
import tk.coaster3000.modscript.util.VersionUtil.Version

abstract class ModScript {
	def reportAddon(wrappedAddonException: WrappedAddonException)
	def log(addon: Addon = null, logLevel: LogLevel, msg: String)

	def getVersionString(): String
	def getVersion(): Version = VersionUtil.parse(getVersionString)

	private val addons = collection.mutable.Map[String, Addon]()

	protected def getAddons = addons

	def getAddon(addonName: String) = addons.get(addonName).orNull

	@throws[AddonAlreadyExistsException]
	def addons_+=(addon: Addon) = addAddon(addon)

	def addons_-=(addon: Addon) = removeAddon(addon)

	@throws[AddonAlreadyExistsException]
	def addAddon(addon: Addon) = {
		if (addons contains addon.getName) throw new AddonAlreadyExistsException(addon)
		if (!(addon isInitialized)) addon.init(this)
		try {
			addon.enable()
			addons.put(addon.getName, addon)
		} catch {
			case e: Throwable => reportAddon(new AddonEnableException(addon, e))
		}
	}

	def clearAddons():Unit = getAddons.keys.foreach(name=> {
		this.removeAddon(name)
	})

	def removeAddon(_addon: String) = {
		val mappedAddon = addons remove _addon
		if (mappedAddon.nonEmpty) {
			val addon = mappedAddon.get
			try {
				addon.disable()
			} catch {
				case e: Throwable => reportAddon(new AddonDisableException(addon, e))
			}
		}
	}

	def removeAddon(_addon: Addon) = {
		val mappedAddon = addons remove _addon.getName
		if (mappedAddon.nonEmpty) {
			val addon = mappedAddon.get
			try {
				addon.disable()
			} catch {
				case e: Throwable => reportAddon(new AddonDisableException(addon, e));
			}
		}
	}
}

object ModScript {
	private var instance: ModScript = null

	protected def setInstance(ms: ModScript) = {
		instance = ms
	}

	def getInstance() = instance
}
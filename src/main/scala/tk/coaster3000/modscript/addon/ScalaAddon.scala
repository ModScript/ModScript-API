package tk.coaster3000.modscript.addon

import tk.coaster3000.modscript.ModScript
import tk.coaster3000.modscript.util.{IncompatibleVersionException, VersionUtil}

trait ScalaAddon extends Addon {
	private var _enabled = false
	private var _isInitialized = false

	def getMeta:AddonMeta

	final def getName:String = getMeta.getName
	final def getDescription:String = getMeta.getDescription
	final def getVersion:String = getMeta.getVersion
	final def getSupportedVersion:String = getMeta.getSupportedVersion
	final def getAuthors:Array[String] = getMeta.getAuthors

	def init(modScript: ModScript) = {
		modScript.addAddon(this)
		try {
			val version = VersionUtil.parse(getSupportedVersion)
			if (!version.isCompatibleWith(modScript.getVersion)) throw new IncompatibleVersionException("Addon version is not compatible with current ModScript version.", new IllegalArgumentException)
			onInit(modScript)
			_isInitialized = true
		} catch {
			case e: Exception => ModScript.getInstance().reportAddon(new WrappedAddonException(this, e))
		}
	}

	def onInit(modScript: ModScript)
	def onEnable()
	def onDisable()

	def isInitialized = _isInitialized

	def disable() = {
		this._enabled = false
		try {
			onDisable()
		} catch {
			case e: Exception => ModScript.getInstance().reportAddon(new WrappedAddonException(this, e))
		}
	}

	def enable() = {
		this._enabled = true
		try {
			onEnable()
		} catch {
			case e: Exception => ModScript.getInstance().reportAddon(new WrappedAddonException(this, e))
		}
	}

	def isEnabled = _enabled
}

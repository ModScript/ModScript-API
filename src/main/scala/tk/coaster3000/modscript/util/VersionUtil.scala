package tk.coaster3000.modscript.util

object VersionUtil {
	case class Version(major: Int, minor: Int, revision: Int, releaseMeta: String = "", buildMeta: String = "") extends Comparable[Version] {

		override def compareTo(o: Version): Int = {
			if (major > o.major) { 1 }
			else if (major == o.major) {
				if (minor > o.minor) { 1 }
				else if (minor == o.minor) {
					if (revision > o.revision) { 1 }
					else 0
				} else -1
			} else -1
		}

		def isCompatibleWith(o: Version): Boolean = {
			if (major == 0 || o.major == 0) return this == o
			if (major != o.major) minor <= o.minor
			else true
		}
	}

	def parse(string: String):Version = {
		val s = string.split('-')
		var md = Array("","")
		if (s.isDefinedAt(0) && s(0).contains('+')) {
			val st = s(0).split('+')
			md(1) = st(1)
			s(0) = st(0)
		} else {
			md = if (s.isDefinedAt(1)) if (s(1).contains('+')) s(1).split('+') else Array(s(1), "") else  Array ("","")
		}
		val metaData = md
		try {
			val version = if (s.isDefinedAt(0)) s(0).split('.').map((x: String) => x.toInt) else throw new IllegalArgumentException("Invalid version string!")
			if (version.length != 3) throw new IllegalArgumentException("Version string must follow the x.y.z format!")
			Version(version(0), version(1), version(2), metaData(0), metaData(1))
		} catch {
			case numFormExc: NumberFormatException =>
				throw new InvalidVersionException("Version string must follow the x.y.z format!", numFormExc)
			case illArg: IllegalArgumentException =>
				throw new InvalidVersionException("Version string must follow the x.y.z format!", illArg)
		}
	}
}
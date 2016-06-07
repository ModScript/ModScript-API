package tk.coaster3000.modscript

import org.scalatest.testng.TestNGSuite
import org.testng.annotations.DataProvider
import org.testng.annotations.{Parameters, Test}
import org.testng.Assert._
import tk.coaster3000.modscript.util.{InvalidVersionException, VersionUtil}
import tk.coaster3000.modscript.util.VersionUtil.Version

class VersionParsingTest extends TestNGSuite {

	@DataProvider(name = "validVersionGen")
	def genValidVersions(): Array[Array[Any]] = {
		Array(
			Array("0.1.0", Version(0, 1, 0)),
			Array("0.2.0", Version(0, 2, 0)),
			Array("1.0.0", Version(1, 0, 0)),
			Array("1.0.1", Version(1, 0, 1)),
			Array("1.1.0", Version(1, 1, 0)),
			Array("1.1.1", Version(1, 1, 1)),
			Array("1.0.1-Beta", Version(1,0,1, "Beta")),
			Array("1.0.1+AF156y9", Version(1,0,1,"","AF156y9")),
			Array("1.1.0-Beta+A1496", Version(1,1,0,"Beta","A1496"))
		)
	}

	@DataProvider(name = "invalidVersionGen")
	def genInvalidVersions(): Array[Array[Any]] = {
		Array(
			Array("0.1"),
			Array("1.0-alpha"),
			Array("1.0."),
			Array("dev"),
			Array("05")
		)
	}

	@Test(dataProvider = "validVersionGen")
	def nmValidVersion(vString: String, expected: Version):Unit = {
		assertEquals(VersionUtil.parse(vString), expected)
	}

	@Test(dataProvider = "invalidVersionGen", expectedExceptions = Array(classOf[InvalidVersionException]))
	def nmInvalidVersion(vString: String):Unit = {
		VersionUtil.parse(vString)
		fail("Exception not generated as expected!")
	}
}

package tk.coaster3000.modscript

import org.scalatest.testng.TestNGSuite
import org.testng.annotations._
import tk.coaster3000.modscript.addon._

class AddonInitializationTest extends TestNGSuite {

	var modScript: DummyModScript = new DummyModScript()

	@AfterMethod
	def cleanup(): Unit = {
		modScript.rethrowMode=false
		modScript.clearAddons()
	}

	@Test
	def testGoodAddonInitialization(): Unit = {
		val goodAddon = new DummyJavaAddon(new DummyAddonMeta("DummyAddon1", "Dummy addon to test api", "0.1.0", "1.0.0", Array("Coaster3000"), 1))
		goodAddon.init(modScript)
	}

	@Test(expectedExceptions = Array(classOf[AddonInitializationException]))
	def testBadAddonInitialization(): Unit = {
		modScript.rethrowMode=true
		val badAddon = new DummyJavaAddon(new DummyAddonMeta("DummyAddon2", "Dummy addon to test api", "0.1.0", "1.0.0", Array("Coaster3000"), 1)) {
			override def onInit(modScript: ModScript): Unit = throw new NullPointerException("Test")
		}
		modScript.addAddon(badAddon)
	}

	@Test
	def testGoodAddonEnable(): Unit = {
		val goodAddon = new DummyJavaAddon(new DummyAddonMeta("DummyAddon1", "Dummy addon to test api", "0.1.0", "1.0.0", Array("Coaster3000"), 1))
		modScript.addAddon(goodAddon)
	}

	@Test(expectedExceptions = Array(classOf[AddonEnableException]))
	def testBadAddonEnable(): Unit = {
		modScript.rethrowMode=true
		val badAddon = new DummyJavaAddon(new DummyAddonMeta("DummyAddon2", "Dummy addon to test api", "1.0.0", "0.1.0", Array("Coaster3000"), 1)) {
			override def onEnable(): Unit = throw new NullPointerException("Test")
		}
		modScript.addAddon(badAddon)
	}

	@Test
	def testGoodAddonDisable(): Unit = {
		val goodAddon = new DummyJavaAddon(new DummyAddonMeta("DummyAddon1", "Dummy addon to test api", "0.1.0", "1.0.0", Array("Coaster3000"), 1))
		modScript.addAddon(goodAddon)
		modScript.removeAddon(goodAddon)
	}

	@Test(expectedExceptions = Array(classOf[AddonDisableException]))
	def testBadAddonDisable(): Unit = {
		modScript.rethrowMode=true
		val badAddon = new DummyJavaAddon(new DummyAddonMeta("DummyAddon2", "Dummy addon to test api", "1.0.0", "0.1.0", Array("Coaster3000"), 1)) {
			override def onDisable(): Unit = throw new NullPointerException("Test")
		}
		modScript.addAddon(badAddon)
		modScript.removeAddon(badAddon)
	}
}

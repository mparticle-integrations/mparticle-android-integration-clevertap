package com.mparticle.kits

import android.content.Context
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class KitTests {
    private val kit: KitIntegration
         get() = CleverTapKit()

    @Test
    @Throws(Exception::class)
    fun testGetName() {
        val name = kit.name
        Assert.assertTrue(name != null && name.isNotEmpty())
    }

    /**
     * Kit *should* throw an exception when they're initialized with the wrong settings.
     *
     */
    @Test
    @Throws(Exception::class)
    fun testOnKitCreate() {
        var e: Exception? = null
        try {
            val kit = kit
            val settings = HashMap<String, String>()
            settings["fake setting"] = "fake"
            kit.onKitCreate(settings, Mockito.mock(Context::class.java))
        } catch (ex: Exception) {
            e = ex
        }
        Assert.assertNotNull(e)
    }

    @Test
    @Throws(Exception::class)
    fun testClassName() {
        val factory = KitIntegrationFactory()
        val integrations = factory.knownIntegrations
        val className = kit.javaClass.name
        for (integration in integrations) {
            if (integration.value == className) {
                return
            }
        }
        Assert.fail("$className not found as a known integration.")
    }
}
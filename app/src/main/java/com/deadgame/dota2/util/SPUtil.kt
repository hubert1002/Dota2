package com.deadgame.dota2.util

import android.content.Context
import android.content.SharedPreferences
import com.deadgame.dota2.DotaApplication

/**
 * Created by liuwei on 2019/12/4.
 * SharedPreferencesUtil 替代类，数据需要分业务场景存储，SCENE 可以自己定义
 */
object SPUtil {

    enum class SCENE {
        Common
    }

    private const val spPrefix = "sp_palmchat_"
    private val PREF_LIST: HashMap<SCENE, SharedPreferences> = HashMap()

//    private val prefs: SharedPreferences by lazy { FrameworkAppContext.getContext().getSharedPreferences(SCENE.COMMON.name, Context.MODE_PRIVATE) }

    fun getSharedPreferences(SCENE: SCENE): SharedPreferences {
        var sp = PREF_LIST[SCENE]
        if (sp == null) {
            var xmlName = spPrefix + SCENE.name
            sp = DotaApplication.BaseContextWrapper.getContext().getSharedPreferences(xmlName, Context.MODE_PRIVATE)
            PREF_LIST.put(SCENE, sp)
        }
        return sp!!
    }

    /**
     * 获取存放数据
     * @return 值
     */
    @Suppress("UNCHECKED_CAST")
    private fun getValue(scene: SCENE, key: String, default: Any): Any? {
        var prefs = getSharedPreferences(scene)
        with(prefs) {
            return when (default) {
                is Int -> getInt(key, default)
                is String -> getString(key, default)
                is Long -> getLong(key, default)
                is Float -> getFloat(key, default)
                is Boolean -> getBoolean(key, default)
                else -> throw IllegalArgumentException("SharedPreferences 类型错误")
            }
        }
    }

    fun getString(scene: SCENE, key: String, default: String = ""): String {
        return getValue(scene, key, default) as String
    }

    fun getInt(scene: SCENE, key: String, default: Int = 0): Int {
        return getValue(scene, key, default) as Int
    }

    fun getLong(scene: SCENE, key: String, default: Long = 0): Long {
        return getValue(scene, key, default) as Long
    }

    fun getBoolean(scene: SCENE, key: String, default: Boolean = false): Boolean {
        return getValue(scene, key, default) as Boolean
    }

    fun getFloat(scene: SCENE, key: String, default: Float = 0f): Float {
        return getValue(scene, key, default) as Float
    }

    /**
     * 存放SharedPreferences
     * @param key 键
     * @param value 值
     */
    fun saveValue(scene: SCENE, key: String, value: Any) {
        var prefs = getSharedPreferences(scene)
        with(prefs.edit()) {
            when (value) {
                is Long -> putLong(key, value)
                is Int -> putInt(key, value)
                is String -> putString(key, value)
                is Float -> putFloat(key, value)
                is Boolean -> putBoolean(key, value)
                else -> throw IllegalArgumentException("SharedPreferences 类型错误")
            }.apply()
        }
    }

    /**
     * 清除
     */
    fun clear(scene: SCENE) {
        var prefs = getSharedPreferences(scene)
        prefs.edit().clear().apply()
    }

    /**
     * 删除某Key的值
     */
    fun remove(scene: SCENE, key: String) {
        var prefs = getSharedPreferences(scene)
        prefs.edit().remove(key).apply()
    }


    fun test() {
        SPUtil.saveValue(SCENE.Common, "a", true)
        SPUtil.saveValue(SCENE.Common, "b", "1")
        SPUtil.saveValue(SCENE.Common, "c", 1)
        SPUtil.saveValue(SCENE.Common, "e", 0.1f)
        SPUtil.saveValue(SCENE.Common, "f", 11212121L)

        var a = SPUtil.getBoolean(SCENE.Common, "a")
        var b = SPUtil.getString(SCENE.Common, "b")
        var c = SPUtil.getInt(SCENE.Common, "c")
        var e = SPUtil.getFloat(SCENE.Common, "e")
        var f = SPUtil.getLong(SCENE.Common, "f")
    }

}
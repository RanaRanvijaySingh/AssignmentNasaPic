package com.simple.simpletestapp.utils

import com.simple.simpletestapp.TestHelper
import com.simple.simpletestapp.domain.uimodels.PicUiModel
import junit.framework.TestCase
import org.junit.Test

class CommonUtilTest : TestCase() {

    private lateinit var listPicUiModel: MutableList<PicUiModel>

    public override fun setUp() {
        listPicUiModel = TestHelper.picUiModelList
    }

    @Test
    fun test_getSortedPicList_if_image_is_null() {
        val actual = listPicUiModel
        val expected = CommonUtil().getSortedPicList(listPicUiModel, null)
        assertEquals(actual, expected)
    }

    @Test
    fun test_getSortedPicList_if_image_is_blank() {
        val actual = listPicUiModel
        val expected = CommonUtil().getSortedPicList(listPicUiModel, "")
        assertEquals(actual, expected)
    }

    @Test
    fun test_getSortedPicList_if_image_is_second_item() {
        // This URL is from the SECOND object from "test>resources>data.json"
        val actualItemUrl = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_960.jpg"
        val expected = CommonUtil().getSortedPicList(
            listPicUiModel,
            "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_960.jpg"
        )
        // After function call the image URL should be at first position.
        assertEquals(actualItemUrl, expected[0].url)
    }

    @Test
    fun test_getSortedPicList_if_image_is_last_item() {
        // This URL is from the LAST object from "test>resources>data.json"
        val actualItemUrl = "https://apod.nasa.gov/apod/image/1912/M33-HaLRGB-RayLiao1024.jpg"
        val expected = CommonUtil().getSortedPicList(
            listPicUiModel,
            "https://apod.nasa.gov/apod/image/1912/M33-HaLRGB-RayLiao1024.jpg"
        )
        // After function call the image URL should be at first position.
        assertEquals(actualItemUrl, expected[0].url)
    }
}

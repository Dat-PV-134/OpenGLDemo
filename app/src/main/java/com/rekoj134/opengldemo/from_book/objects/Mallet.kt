package com.rekoj134.opengldemo.from_book.objects

import android.opengl.GLES20.GL_POINTS
import android.opengl.GLES20.glDrawArrays
import com.rekoj134.opengldemo.from_book.constant.BYTES_PER_FLOAT
import com.rekoj134.opengldemo.from_book.data.VertexArray
import com.rekoj134.opengldemo.from_book.programs.ColorShaderProgram
import com.rekoj134.opengldemo.util.Geometry
import javax.microedition.khronos.opengles.GL

class Mallet(
    radius: Float,
    val height: Float,
    numPointsAroundMallet: Int
) {
    private val POSITION_COMPONENT_COUNT = 3
    private var vertexArray: VertexArray
    private val drawList by lazy { ArrayList<ObjectBuilder.DrawCommand>() }

    init {
        val generatedData = ObjectBuilder.createMallet(Geometry.Point(0f, 0f, 0f), radius, height,
            numPointsAroundMallet
        )
        vertexArray = VertexArray(generatedData.vertexData)
        drawList.addAll(generatedData.drawList)
    }

    fun bindData(colorProgram: ColorShaderProgram) {
        vertexArray.setVertexAttributePointer(
            0,
            colorProgram.getPositionAttributeLocation(),
            POSITION_COMPONENT_COUNT,
            0
        )
    }

    fun draw() {
        drawList.forEach {
            it.draw()
        }
    }
}
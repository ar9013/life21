package com.ar9013.gdx21;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

import javax.swing.text.AttributeSet;
import java.util.EventListener;

public class Gdx21 extends ApplicationAdapter implements InputProcessor{
	private PerspectiveCamera perspectiveCamera;
	private ModelBatch modelBatch; // 把 素材（模型、貼圖）放入，加速渲染
	private ModelBuilder builder;
	private Model box;
	private ModelInstance modelInstance;
	private Environment environment;
	
	@Override
	public void create () {
		perspectiveCamera = new PerspectiveCamera(75,
				Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		// 上下是 Y(正方向向上) ,前後是 Z（正方向向後）,左右是 X（正方向向左）
				perspectiveCamera.position.set(0f,0f,3f);

				perspectiveCamera.lookAt(0f,0f,0f);
				perspectiveCamera.near = 0.1f;
				perspectiveCamera.far = 300f; //設定很大，會渲染比較遠的場景。

		modelBatch = new ModelBatch();
		builder = new ModelBuilder();
		box = builder.createBox(2f,2f,2f,new Material(ColorAttribute.createDiffuse(Color.PINK)),
				VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal);

		modelInstance = new ModelInstance(box,0,0,0);
		environment = new Environment();

		environment.set(new ColorAttribute(ColorAttribute.AmbientLight,0.8f,0.8f,0.8f,1.0f));

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);

		perspectiveCamera.update();

		modelBatch.begin(perspectiveCamera);
		modelBatch.render(modelInstance,environment);
		modelBatch.end();
	}
	
	@Override
	public void dispose () {

		modelBatch.dispose();
		box.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {

		// In the real world, do not create NEW variables over and over, create
		// a temporary static member instead
		if(keycode == Input.Keys.LEFT)
			perspectiveCamera.rotateAround(new Vector3(0f, 0f, 0f), new Vector3(0f, 1f, 0f), 1f);
		if(keycode == Input.Keys.RIGHT)
			perspectiveCamera.rotateAround(new Vector3(0f,0f,0f),new Vector3(0f,1f,0f), -1f);

		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}

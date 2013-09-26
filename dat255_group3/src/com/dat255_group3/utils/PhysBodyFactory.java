package com.dat255_group3.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class PhysBodyFactory {

	
	public static Body createCharacter(World physWorld, Vector2 pos, Vector2 size) {
		PolygonShape shape;
		FixtureDef fixtureDef = new FixtureDef();
		BodyDef bodyDef = new BodyDef();
		Body body = null;


		shape = new PolygonShape();
		shape.setAsBox(size.x/2, size.y/2);

		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0.8f;
		fixtureDef.restitution = 0f;

		bodyDef.type = BodyType.DynamicBody;
		bodyDef.fixedRotation = true;
		bodyDef.position.set(pos.x, pos.y);

		body = physWorld.createBody(bodyDef);
		body.createFixture(fixtureDef);

		return body;
	}
	
	
	public static void addSolidGround(final Vector2 pos, final Vector2 size, final float friction, final float restitution, World physWorld) {
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(size.x, size.y);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = polygonShape;
		fixtureDef.friction = friction;
		fixtureDef.density = 1f;
		fixtureDef.restitution = restitution;

		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(pos);
		bodyDef.type = BodyType.StaticBody;
		bodyDef.fixedRotation = true;

		Body body = physWorld.createBody(bodyDef);
		body.createFixture(fixtureDef);
	}
}

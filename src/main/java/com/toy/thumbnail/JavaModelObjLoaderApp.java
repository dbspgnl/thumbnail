package com.toy.thumbnail;

import java.applet.Applet;
import java.awt.BorderLayout;

import javax.media.j3d.Alpha;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.toy.thumbnail.utils.ObjFileReader;

public class JavaModelObjLoaderApp extends Applet {
	private static final long serialVersionUID = 5841679659336190804L;

	public BranchGroup createSceneGraph() {
		BranchGroup group = new BranchGroup();
		TransformGroup transGroup = new TransformGroup();
		Transform3D trans3d = new Transform3D();
		trans3d.setScale(0.8);
		transGroup.setTransform(trans3d);
		group.addChild(transGroup);

		BoundingSphere bound = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
		Color3f bgColor = new Color3f(0.05f, 0.05f, 0.2f);
		Background bg = new Background(bgColor);
		bg.setApplicationBounds(bound);
		group.addChild(bg);
		Color3f lightColor = new Color3f(1.0f, 1.0f, 0.9f);
		Vector3f lightDirection = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light = new DirectionalLight(lightColor, lightDirection);
		light.setInfluencingBounds(bound);
		group.addChild(light);
		TransformGroup objTrans = new TransformGroup();
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		objTrans.addChild(new ObjFileReader("D:/data/Mercedes.obj"));

		transGroup.addChild(objTrans);

		group.compile();

		return group;
	}

	public JavaModelObjLoaderApp() {

		setLayout(new BorderLayout());
		Canvas3D canvas = new Canvas3D(null);
		add("Center", canvas);
		BranchGroup scene = createSceneGraph();

		SimpleUniverse universe = new SimpleUniverse(canvas);
		universe.getViewingPlatform().setNominalViewingTransform();
		universe.addBranchGraph(scene);
	}

	public static void main(String[] args) {
		new MainFrame(new JavaModelObjLoaderApp(), 360, 360);
	}

}
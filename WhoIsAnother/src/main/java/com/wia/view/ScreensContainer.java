/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License"). You
 * may not use this file except in compliance with the License. You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.wia.view;

import java.util.HashMap;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wia.controller.AbstractFXController;

/**
 * 
 * @author Angie and Saint Scott
 */
public class ScreensContainer extends StackPane {
	// Holds the screens to be displayed

	private final static Logger logger = LoggerFactory
			.getLogger(ScreensContainer.class);

	private final HashMap<String, AbstractFXController> controllers = new HashMap<>();
	private final HashMap<String, String> screenMap = new HashMap<>();
	private final HashMap<String, Parent> screens = new HashMap<>();

	private String currentScreen = null;

	public ScreensContainer() {
		super();
	}

	/**
	 * register the screen name and its resource into the container
	 * 
	 * @param name
	 *            screen'name
	 * @param resource
	 *            screen's fxml file path
	 */
	public void registerScreen(String name, String resource) {
		screenMap.put(name, resource);
	}

	private String getResource(String name) {
		return screenMap.get(name);
	}

	/**
	 * Add the Controller to the collection
	 * 
	 * @param name
	 * @param screen
	 */
	private void addController(String name, AbstractFXController screen) {
		controllers.put(name, screen);
	}

	/**
	 * Returns the Controller binded with the appropriate name
	 * 
	 * @param name
	 * @return
	 */
	public AbstractFXController getController(String name) {
		return controllers.get(name);
	}

	/**
	 * @param name
	 * @param screen
	 */
	private void addScreen(String name, Parent screen) {
		screens.put(name, screen);
	}

	/**
	 * @param name
	 * @return
	 */
	private Parent getScreen(String name) {
		return screens.get(name);
	}

	/**
	 * Loads the fxml file, add the screen to the screens collection and finally
	 * injects the screenPane to the controller.
	 * 
	 * @param name
	 */
	private void loadScreen(String name) {
		try {
			logger.info("load the {}'s fxml", name);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(
					screenMap.get(name)));
			addScreen(name, loader.load());
			AbstractFXController myScreenControler = loader.getController();
			addController(name, myScreenControler);
			myScreenControler.setScreenContainer(this);
			myScreenControler.init();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method tries to displayed the screen with a predefined name. First
	 * it makes sure the screen has been already loaded. Then if there is more
	 * than one screen the new screen is been added second, and then the current
	 * screen is removed. If there isn't any screen being displayed, the new
	 * screen is just added to the root.
	 * 
	 * @param name
	 * @return if set screen succeed
	 */
	public boolean setScreen(final String name) {
		if (name == currentScreen) {
			return true;
		}
		if (getResource(name) != null) { // screen loaded
			loadScreen(name);
			currentScreen = name;

			if (!getChildren().isEmpty()) { // if there is more than one screen
				FadeTransition fadeOut = new FadeTransition(new Duration(1000),
						this);
				fadeOut.setFromValue(1.0);
				fadeOut.setToValue(0.0);
				fadeOut.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						getChildren().clear(); // remove the displayed screen
						getChildren().add(getScreen(name)); // add the screen
						FadeTransition fadeIn = new FadeTransition(
								new Duration(800), ScreensContainer.this);
						fadeIn.setFromValue(0.0);
						fadeIn.setToValue(1.0);
						fadeIn.play();
					}
				});
				fadeOut.play();

			} else {
				setOpacity(0.0);
				getChildren().add(getScreen(name)); // no one else been
													// displayed, then just show
				FadeTransition fadeIn = new FadeTransition(new Duration(1000),
						this);
				fadeIn.setFromValue(0.0);
				fadeIn.setToValue(1.0);
				fadeIn.play();
			}
			return true;
		} else {
			logger.error("screen's address {} hasn't been registered!!!", name);
			return false;
		}
	}

	/**
	 * This method will remove the screen with the given name from the
	 * collection of screens
	 * 
	 * @param name
	 * @return if unload screen succeed
	 */
	public boolean unloadScreen(String name) {
		if (controllers.remove(name) == null) {
			logger.warn("Screen {} didn't exist", name);
			return false;
		} else {
			return true;
		}
	}
}

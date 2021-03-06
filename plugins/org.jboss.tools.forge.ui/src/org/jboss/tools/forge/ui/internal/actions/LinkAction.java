/**
 * Copyright (c) Red Hat, Inc., contributors and others 2013 - 2014. All rights reserved
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.tools.forge.ui.internal.actions;

import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.jboss.tools.forge.core.runtime.ForgeRuntime;
import org.jboss.tools.forge.core.runtime.ForgeRuntimeState;
import org.jboss.tools.forge.ui.internal.ForgeUIPlugin;
import org.jboss.tools.forge.ui.internal.part.SelectionSynchronizer;

public class LinkAction extends Action {
	
	private SelectionSynchronizer selectionSynchronizer;
	private ForgeRuntime runtime;
	
	public LinkAction(ForgeRuntime runtime) {
		super("", SWT.TOGGLE);
		this.runtime = runtime;
		this.selectionSynchronizer = new SelectionSynchronizer(runtime);
		setImageDescriptor(createImageDescriptor());
		setToolTipText("Link With Editor");
	}

	@Override
	public void run() {
		selectionSynchronizer.setEnabled(isChecked());
	}
	
	@Override
	public boolean isEnabled() {
		return ForgeRuntimeState.RUNNING.equals(runtime.getState());
	}

	private ImageDescriptor createImageDescriptor() {
		URL url = ForgeUIPlugin.getDefault().getBundle().getEntry("icons/link.gif");
		return ImageDescriptor.createFromURL(url);
	}

}

/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.tools.forge.ui.ext.quickaccess.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.jboss.forge.addon.ui.UICommand;
import org.jboss.tools.forge.ui.ext.quickaccess.QuickAccessElement;
import org.jboss.tools.forge.ui.ext.quickaccess.QuickAccessProvider;

public class ForgeQuickAccessProvider extends QuickAccessProvider implements
		Comparable<ForgeQuickAccessProvider> {

	private Map<String, QuickAccessElement> candidates = new HashMap<String, QuickAccessElement>();
	private String category;

	public ForgeQuickAccessProvider(String category, List<UICommand> commands) {
		this.category = category;
		if (commands != null)
			for (UICommand command : commands) {
				ForgeQuickAccessElement elem = new ForgeQuickAccessElement(
						this, command);
				candidates.put(elem.getId(), elem);
			}
	}

	@Override
	public String getId() {
		return "forge-" + category;
	}

	@Override
	public String getName() {
		return category;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public List<QuickAccessElement> getElements() {
		return new ArrayList<QuickAccessElement>(candidates.values());
	}

	@Override
	public QuickAccessElement getElementForId(String id) {
		return candidates.get(id);
	}

	@Override
	protected void doReset() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ForgeQuickAccessProvider other = (ForgeQuickAccessProvider) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		return true;
	}

	@Override
	public int compareTo(ForgeQuickAccessProvider o) {
		return getName().compareTo(o.getName());
	}
}

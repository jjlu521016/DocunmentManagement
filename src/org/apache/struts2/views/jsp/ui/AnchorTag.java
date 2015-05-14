/*
 * $Id: AnchorTag.java 768855 2009-04-27 02:09:35Z wesw $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.struts2.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import org.apache.struts2.components.Anchor;
import org.apache.struts2.components.Component;



import com.bysj.docmanage.domain.User;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * @see Anchor
 * @author jielu
 * 本文件是<s:a>...</s:a>标签调用的类。因为本系统要根据不用的角色控制不同的权限，来显示不同的功能按钮，
 * 本系统的按钮是通过超链接来实现的，所以在执行超链接标签<s:a>前，利用权限拦截来显示不同的按钮
 */

public class AnchorTag extends AbstractClosingTag {

	private static final long serialVersionUID = -1034616578492431113L;

	protected String href;
	protected String includeParams;
	protected String scheme;
	protected String action;
	protected String namespace;
	protected String method;
	protected String encode;
	protected String includeContext;
	protected String escapeAmp;
	protected String portletMode;
	protected String windowState;
	protected String portletUrlType;
	protected String anchor;
	protected String forceAddSchemeHostAndPort;
	
///////////////////////////////////////////////////////////////////////////////
	/**
	 * 重写doEndTag方法，用来过滤功能显示的权限
	 * @lujie 20150302
	 * 
	 */
	@Override
	public int doEndTag() throws JspException {
		// 根据session获取当前登录用户
		User user = (User) pageContext.getSession().getAttribute("user");
		/**
		 *  当前准备显示的链接对应的权限URL
		 *  因为数据库存取的action为“/action”形式，所以 在开头加上'/'
		 */		
		String PermUrl =  action;

		if (user.hasPermissionByUrl(PermUrl)) {
			return super.doEndTag(); // 正常的生成并显示超链接标签，并继续执行页面中后面的代码
		} else {
			return EVAL_PAGE; // 不生成与显示超链接标签，只是继续执行页面中后面的代码
		}
	}

///////////////////////////////////////////////////////////////////////////////	
	
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		return new Anchor(stack, req, res);
	}

	protected void populateParams() {
		super.populateParams();

		Anchor tag = (Anchor) component;
		tag.setHref(href);
		tag.setIncludeParams(includeParams);
		tag.setScheme(scheme);
		tag.setValue(value);
		tag.setMethod(method);
		tag.setNamespace(namespace);
		tag.setAction(action);
		tag.setPortletMode(portletMode);
		tag.setPortletUrlType(portletUrlType);
		tag.setWindowState(windowState);
		tag.setAnchor(anchor);

		if (encode != null) {
			tag.setEncode(Boolean.valueOf(encode).booleanValue());
		}
		if (includeContext != null) {
			tag.setIncludeContext(Boolean.valueOf(includeContext).booleanValue());
		}
		if (escapeAmp != null) {
			tag.setEscapeAmp(Boolean.valueOf(escapeAmp).booleanValue());
		}
		if (forceAddSchemeHostAndPort != null) {
			tag.setForceAddSchemeHostAndPort(Boolean.valueOf(forceAddSchemeHostAndPort).booleanValue());
		}
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public void setIncludeContext(String includeContext) {
		this.includeContext = includeContext;
	}

	public void setEscapeAmp(String escapeAmp) {
		this.escapeAmp = escapeAmp;
	}

	public void setIncludeParams(String name) {
		includeParams = name;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setPortletMode(String portletMode) {
		this.portletMode = portletMode;
	}

	public void setPortletUrlType(String portletUrlType) {
		this.portletUrlType = portletUrlType;
	}

	public void setWindowState(String windowState) {
		this.windowState = windowState;
	}

	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}

	public void setForceAddSchemeHostAndPort(String forceAddSchemeHostAndPort) {
		this.forceAddSchemeHostAndPort = forceAddSchemeHostAndPort;
	}
}

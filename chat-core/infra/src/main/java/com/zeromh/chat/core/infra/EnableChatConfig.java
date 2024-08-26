/*
 *
 *  EnableWorksDatabase.java 2023-04-27
 *
 *  Copyright 2023 WorksMobile Corp. All rights Reserved.
 *  WorksMobile PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.zeromh.chat.core.infra;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ChatConfigImportSelector.class)
public @interface EnableChatConfig {

	ChatConfigGroup[] value();

}

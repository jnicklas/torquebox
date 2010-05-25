package org.torquebox.mc;

import org.jboss.beans.metadata.spi.BeanMetaData;
import org.jboss.deployers.structure.spi.DeploymentUnit;
import org.jboss.logging.Logger;

public class AttachmentUtils {
	private static final Logger log = Logger.getLogger( AttachmentUtils.class );
	
	public static void attach(DeploymentUnit unit, BeanMetaData bmd) {
		log.info( "attach: " + bmd.getName() + "\n" + bmd.getBean() );
		String attachmentName = BeanMetaData.class.getName() + "$" + bmd.getName();
		unit.addAttachment( attachmentName, bmd, BeanMetaData.class );
	}
	
	public static void multipleAttach(DeploymentUnit unit, Object metaData, String name) {
		log.info( "multipleAttach: " + metaData.getClass().getName() + "\n" + name );
		unit.addAttachment( metaData.getClass().getName() + '$' + name, metaData );
	}
	
	public static String beanName(DeploymentUnit unit, Class<?> beanClass) {
		return beanName(unit, beanClass, null );
	}
	
	public static String beanName(DeploymentUnit unit, Class<?> beanClass, String name) {
		String simpleClassName = beanClass.getName();
		int lastDot = simpleClassName.lastIndexOf( '.' );
		
		if ( lastDot > 0 ) {
			simpleClassName = simpleClassName.substring( lastDot + 1 );
		}
		
		return beanName( unit, simpleClassName, name );
	}
	
	public static String beanName(DeploymentUnit unit, String category, String name) {
		if ( name == null ) {
			return "torquebox." + unit.getSimpleName() + "." + category;
		} else {
			return "torquebox." + unit.getSimpleName() + "." + category + "." + name;
		}
	}

}

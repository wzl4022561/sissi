package com.sissi.write.jaxb;

import java.util.HashMap;
import java.util.Map;

import com.sissi.protocol.Stream;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

/**
 * @author Kim.shen 2013-10-15
 */
public class JAXBNamespaceMapping extends NamespacePrefixMapper {
	
	private final Map<String, String> mapping = new HashMap<String, String>();

	public JAXBNamespaceMapping() {
		super();
		this.mapping.put(Stream.XMLNS, Stream.NAME);
	}

	public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
		return this.mapping.get(namespaceUri);
	}
}

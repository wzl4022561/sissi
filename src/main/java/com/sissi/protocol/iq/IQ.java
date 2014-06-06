package com.sissi.protocol.iq;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import com.sissi.io.read.Collector;
import com.sissi.io.read.Metadata;
import com.sissi.protocol.Element;
import com.sissi.protocol.Protocol;
import com.sissi.protocol.error.ServerError;
import com.sissi.protocol.iq.ack.ServerAck;
import com.sissi.protocol.iq.bind.Bind;
import com.sissi.protocol.iq.block.BlockList;
import com.sissi.protocol.iq.block.Blocked;
import com.sissi.protocol.iq.block.UnBlock;
import com.sissi.protocol.iq.bytestreams.Bytestreams;
import com.sissi.protocol.iq.disco.DiscoInfo;
import com.sissi.protocol.iq.disco.DiscoItems;
import com.sissi.protocol.iq.last.Last;
import com.sissi.protocol.iq.last.Last.LastSeconds;
import com.sissi.protocol.iq.ping.Ping;
import com.sissi.protocol.iq.privated.Privated;
import com.sissi.protocol.iq.register.Register;
import com.sissi.protocol.iq.roster.Roster;
import com.sissi.protocol.iq.search.Search;
import com.sissi.protocol.iq.session.Session;
import com.sissi.protocol.iq.sharedgroup.Sharedgroup;
import com.sissi.protocol.iq.si.Si;
import com.sissi.protocol.iq.time.Time;
import com.sissi.protocol.iq.vcard.VCard;
import com.sissi.protocol.iq.version.Client;
import com.sissi.protocol.muc.Owner;
import com.sissi.protocol.muc.Unique;
import com.sissi.protocol.muc.Unique.UniqueSequence;
import com.sissi.protocol.muc.XMucAdmin;

/**
 * @author Kim.shen 2013-10-16
 */
@Metadata(uri = IQ.XMLNS, localName = IQ.NAME)
@XmlRootElement
public class IQ extends Protocol implements Collector {

	public final static String XMLNS = "jabber:client";

	public final static String NAME = "iq";

	private final static List<Protocol> empty = new ArrayList<Protocol>();

	private List<Element> extras;

	private List<Protocol> protocols;

	public IQ setId(String id) {
		super.setId(id);
		return this;
	}

	public IQ setFrom(String from) {
		super.setFrom(from);
		return this;
	}

	@XmlElements({ @XmlElement(name = Search.NAME, type = Search.class), @XmlElement(name = Privated.NAME, type = Privated.class), @XmlElement(name = Unique.NAME, type = Unique.class), @XmlElement(name = Sharedgroup.NAME, type = Sharedgroup.class), @XmlElement(name = Owner.NAME, type = Owner.class), @XmlElement(name = XMucAdmin.NAME, type = XMucAdmin.class), @XmlElement(name = ServerAck.NAME, type = ServerAck.class), @XmlElement(name = Client.NAME, type = Client.class), @XmlElement(name = Time.NAME, type = Time.class), @XmlElement(name = Last.NAME, type = Last.class), @XmlElement(name = Last.NAME, type = LastSeconds.class), @XmlElement(name = Ping.NAME, type = Ping.class), @XmlElement(name = Si.NAME, type = Si.class), @XmlElement(name = VCard.NAME, type = VCard.class), @XmlElement(name = Bind.NAME, type = Bind.class), @XmlElement(name = Session.NAME, type = Session.class), @XmlElement(name = Roster.NAME, type = Roster.class), @XmlElement(name = Register.NAME, type = Register.class), @XmlElement(name = DiscoInfo.NAME, type = DiscoInfo.class), @XmlElement(name = DiscoItems.NAME, type = DiscoItems.class), @XmlElement(name = Bytestreams.NAME, type = Bytestreams.class), @XmlElement(name = Blocked.NAME, type = Blocked.class), @XmlElement(name = UnBlock.NAME, type = UnBlock.class), @XmlElement(name = BlockList.NAME, type = BlockList.class) })
	public List<Protocol> getProtocols() {
		return this.protocols;
	}

	@XmlElements({ @XmlElement(name = Unique.NAME, type = UniqueSequence.class), @XmlElement(name = Last.NAME, type = LastSeconds.class) })
	public List<Element> getExtras() {
		return this.extras;
	}

	public IQ add(Protocol protocol) {
		if (this.protocols == null) {
			this.protocols = new ArrayList<Protocol>();
		}
		this.protocols.add(protocol.parent(this));
		return this;
	}

	public IQ add(Element extra) {
		if (this.extras == null) {
			this.extras = new ArrayList<Element>();
		}
		this.extras.add(extra);
		return this;
	}

	public List<Protocol> list() {
		return this.protocols != null ? this.protocols : empty;
	}

	@XmlElement
	public ServerError getError() {
		return super.getError();
	}

	public IQ clear() {
		this.protocols = null;
		this.extras = null;
		return this;
	}

	@Override
	public void set(String localName, Object ob) {
		this.add(Protocol.class.cast(ob));
	}
}

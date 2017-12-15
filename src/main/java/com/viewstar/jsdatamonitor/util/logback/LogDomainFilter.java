package com.viewstar.jsdatamonitor.util.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LogDomainFilter extends Filter<ILoggingEvent> {
	private  String domain;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public FilterReply decide(ILoggingEvent event) {

		if (event.getLoggerName() != null && event.getLoggerName().contains(domain)) {

			return FilterReply.ACCEPT;
		} else {
			return FilterReply.DENY;
		}
	}

}

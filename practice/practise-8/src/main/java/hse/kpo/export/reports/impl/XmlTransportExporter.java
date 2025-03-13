package hse.kpo.export.reports.impl;

import hse.kpo.export.reports.TransportExporter;
import hse.kpo.interfaces.Transport;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class XmlTransportExporter implements TransportExporter {
    @Override
    public void export(List<Transport> transports, Writer writer) throws IOException {
        transports.forEach(transport -> {
                    try {
                        writer.write(String.format("%d,%s,%s\n",
                                transport.getVin(),
                                transport.getTransportType(),
                                transport.getEngineType()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}

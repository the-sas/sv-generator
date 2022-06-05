package PacketForm;

import java.io.IOException;
import java.util.List;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;


public class DevSelect {
    public final PcapNetworkInterface selectNetworkInterface() throws IOException {
        List<PcapNetworkInterface> allDevs = null;
        try {
            allDevs = Pcaps.findAllDevs();
        } catch (PcapNativeException e) {
            throw new IOException(e.getMessage());
        }

        if (allDevs == null || allDevs.isEmpty()) {
            throw new IOException("No NIF to capture.");
        }
        return doSelect(allDevs);
    }
    public PcapNetworkInterface doSelect(List<PcapNetworkInterface> nifs) {
        int nifIdx = 0; /** Всегода выбирается 1-й сетевой адаптер */
        return nifs.get(nifIdx);
    }
}

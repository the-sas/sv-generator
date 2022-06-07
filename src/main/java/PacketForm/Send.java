package PacketForm;

import org.pcap4j.core.*;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.UnknownPacket;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.namednumber.EtherType;
import org.pcap4j.util.MacAddress;
import java.io.IOException;

@SuppressWarnings("javadoc")
public class Send {
    private static final String READ_TIMEOUT_KEY = Send.class.getName() + ".readTimeout";
    private static final int READ_TIMEOUT = Integer.getInteger(READ_TIMEOUT_KEY, 10); // [ms]
    private static final String SNAPLEN_KEY = Send.class.getName() + ".snaplen";
    private static final int SNAPLEN = Integer.getInteger(SNAPLEN_KEY, 65536); // [bytes]
    private static short count = 0;

    public Send(String dMAC, String srcMAC, String svID, byte[] appID, short num, int[] meas, int[] qual) throws Exception {
        long time = System.currentTimeMillis();
        PcapNetworkInterface nif;
        try {
            nif = new DevSelect().selectNetworkInterface();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (nif == null) {
            return;
        }
        PcapHandle sendHandle = nif.openLive(SNAPLEN, PromiscuousMode.PROMISCUOUS, READ_TIMEOUT);
        for (int i = 0; i < num; i++) {
            long t = getTimeInSeconds(time);
            double wt = 2 * Math.PI * 50 * t / 1000;
                APDU apdu = new APDU(appID);
                ASDU asdu = new ASDU(svID, (short) count, new int[]{
                        (int) (meas[0] * 1000 * Math.sin(wt + Math.toRadians(meas[1]))), qual[0],
                        (int) (meas[2] * 1000 * Math.sin(wt + Math.toRadians(meas[3]))), qual[1],
                        (int) (meas[4] * 1000 * Math.sin(wt + Math.toRadians(meas[5]))), qual[2],
                        (int) (meas[0] * 1000 * Math.sin(wt + Math.toRadians(meas[1]))) +
                        (int) (meas[2] * 1000 * Math.sin(wt + Math.toRadians(meas[3]))) +
                        (int) (meas[4] * 1000 * Math.sin(wt + Math.toRadians(meas[5]))), qual[3],

                        (int) (meas[6] * 1000 * Math.sin(wt + Math.toRadians(meas[7]))), qual[4],
                        (int) (meas[8] * 1000 * Math.sin(wt + Math.toRadians(meas[9]))), qual[5],
                        (int) (meas[10] * 1000 * Math.sin(wt + Math.toRadians(meas[11]))), qual[6],
                        (int) (meas[6] * 1000 * Math.sin(wt + Math.toRadians(meas[7]))) +
                        (int) (meas[8] * 1000 * Math.sin(wt + Math.toRadians(meas[9]))) +
                        (int) (meas[10] * 1000 * Math.sin(wt + Math.toRadians(meas[11]))), qual[7]});

                byte[] payload = new byte[apdu.convertToBytes().length + asdu.convertToBytes().length];
                System.arraycopy(apdu.convertToBytes(), 0, payload, 0, apdu.convertToBytes().length);
                System.arraycopy(asdu.convertToBytes(), 0, payload, apdu.convertToBytes().length, asdu.convertToBytes().length);

                UnknownPacket.Builder svBuilder = new UnknownPacket.Builder();
                svBuilder.rawData(payload);

                EthernetPacket.Builder etherBuilder = new EthernetPacket.Builder();
                etherBuilder
                        .dstAddr(MacAddress.getByName(dMAC))
                        .srcAddr(MacAddress.getByName(srcMAC))
                        .type(new EtherType((short) 0x88ba, "Sampled Values"))
                        .payloadBuilder(svBuilder)
                        .paddingAtBuild(true);

                Packet p = etherBuilder.build();
                sendHandle.sendPacket(p);

                count += 1;
                Thread.sleep((long) 0.25);
        }
    }
    public static int getTimeInSeconds(long time){
        long nowTime = System.currentTimeMillis();
        return (int)((nowTime-time));
    }
}


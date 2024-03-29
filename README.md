﻿# SV_gen_iec61850
IEC 61850 (IEC 61850 – Communication Networks and Systems in Substations) standard defines Sampled Values (SV) protocol as a publisher/subscriber type communication. This protocol is used for information exchange between Merging Units and IEDs (IED – Intelligent Electronic Device) in a Substation over the Ethernet. 
The concept of SV communication is that the publisher periodically sends messages with exactly defined time intervals. The time interval depends on two factors: measured signal frequency and Samples Per Period (SPP). IEC 61850-9-2LE defines two SPP values of 80 and 256. So, for example, if the measured signal frequency is 50 Hz and SPP is 80, the sending time interval will be 1/50/80, or 250 µs.
The protocol defines the SV message as shown on the figure 1.

![изображение](https://user-images.githubusercontent.com/90500480/200071849-822c18ce-2d06-4111-abab-36db5e0ea40c.png)

Figure 1 - SV message content

The APDU field contains the payload of the SV messages. Every APDU contains up to 8 ASDU (Application Specific Data Unit), where each ASDU contains one three phase current and voltage measurements and each ASDU has a unique SV identification value. The content of an APDU can be found on the figure 2.

![изображение](https://user-images.githubusercontent.com/90500480/200072003-4aad1500-e7c5-43c9-a0fa-468778ca782d.png)

Figure 2 - APDU field content

Sequence of Data on figure 2 - sequence of measured voltage and current values as shown on Sequence of Data encoding figure 3.

![изображение](https://user-images.githubusercontent.com/90500480/200072222-7e5ae757-940e-4cb1-b762-0f8ff9481308.png)

Figure 3 - Sequence of Data encoding

In this project, an SV generator was developed in Java using the pcap 4j library for generating SV packages and the JavaFX platform for implementing a graphical interface (figure 4).

![изображение](https://user-images.githubusercontent.com/90500480/200073284-139f2c46-e7f6-4d25-9c7c-0c052067eb0b.png)

Figure 4 - Graphical interface 

Validation of input values was also implemented (figure 5).

![изображение](https://user-images.githubusercontent.com/90500480/200073573-29769591-2c2c-4955-a39c-43322f20e0a6.png)

Figure 5 - The example of an error on an incorrectly entered value

The example of the generated SV package is shown in Figure 6.
![изображение](https://user-images.githubusercontent.com/90500480/200073878-42a401b8-24b1-4212-bebc-364b7cd4d2ad.png)

Figure 6 - Analysis of the generated SV packet using Wireshark

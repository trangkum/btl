package Module.Enum;

import com.google.common.collect.Lists;

public class EnumValue {
    public static Integer Parse(TicketStatus ticketStatus) {
        return Lists.newArrayList(TicketStatus.values()).indexOf(ticketStatus) + 1;
    }

    public static Integer Parse(ReadStatus readStatus) {
        if (readStatus == ReadStatus.UnRead) return 0;
        return 1;
    }
}

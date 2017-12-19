export class PriorityType {
    public static LOW: number = 1;
    public static NORMAL: number = 2;
    public static HIGH: number = 3;
    public static URGENT: number = 4;

}

export const PriorityList: Array<any> =
    [
        {name: "Chọn độ ưu tiên", type: null},
        {name: "Thấp", type: PriorityType.LOW},
        {name: "Bình thường", type: PriorityType.NORMAL},
        {name: "Cao", type: PriorityType.HIGH},
        {name: "Khẩn cấp", type: PriorityType.URGENT},
    ];

export class TicketStatus{
    public static New: number = 1;
    public static InProgress: number = 2;
    public static Resolved: number = 3;
    public static Feedback: number = 4;
    public static Closed: number = 5;
    public static Cancelled: number = 6;
}

export const TicketStatusList: Array<any> =
    [
        {name: "Chọn trạng thái", type: null},
        {name: "New", type: TicketStatus.New},
        {name: "InProgress", type: TicketStatus.InProgress},
        {name: "Resolved", type: TicketStatus.Resolved},
        {name: "Feedback", type: TicketStatus.Feedback},
        {name: "Closed", type: TicketStatus.Closed},
        {name: "Cancelled", type: TicketStatus.Cancelled},
    ];

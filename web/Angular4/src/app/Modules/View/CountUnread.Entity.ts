export class CountUnreadEntity {
    public All: number = 0;
    public New: number = 0;
    public InProgress: number = 0;
    public Resolved: number = 0;
    public Feedback: number = 0;
    public Closed: number = 0;
    public Cancelled: number = 0;
    public OutOfDate: number = 0;
    constructor(data: any = null) {
        if (data == null) {

        } else {
            this.All = data.All;
            this.OutOfDate = data.OutOfDate;
            this.New = data.New;
            this.InProgress = data.InProgress;
            this.Resolved = data.Resolved;
            this.Feedback = data.Feedback;
            this.Closed = data.Closed;
            this.Cancelled = data.Cancelled;
        }
    }
}
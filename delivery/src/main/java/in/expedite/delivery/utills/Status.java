package in.expedite.delivery.utills;

public enum Status {
		
		NEW("New"),
		SUBMITTED_FOR_APPROVAL("SubmittedForApproval"),
		APPROVED("Approved"),
		SENT_FOR_REVIEW("SentForReview"),
		CANCEL("Cancel");
		
		private String status;
		
		private Status(final String status){
			this.status = status;
		}
		
		public String getStatus(){
			return this.status;
		}

		@Override
		public String toString(){
			return this.status;
		}

}

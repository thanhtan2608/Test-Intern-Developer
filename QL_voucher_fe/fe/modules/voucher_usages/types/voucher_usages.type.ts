export interface VoucherUsageRequest {
  voucherCode: string;
  userId: number;
}

export interface VoucherUsageResponse {
  id: number;
  userName: string;
  voucherCode: string;
  usedAt: string; // ISO Date string từ Backend
}
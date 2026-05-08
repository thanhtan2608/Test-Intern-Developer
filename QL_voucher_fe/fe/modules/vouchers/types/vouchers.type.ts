export interface VoucherRequest {
  code: string;
  discountPercent: number;
  quantity: number;
  expiredDate: string; // ISO string (YYYY-MM-DD)
  status: string;
}

export interface VoucherResponse {
  id: number;
  code: string;
  discountPercent: number;
  quantity: number;
  expiredDate: string;
  status: string;
  createdAt: string;
}
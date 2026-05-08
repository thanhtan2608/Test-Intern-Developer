import { VoucherRequest, VoucherResponse } from "../types/vouchers.type";
const API_URL = "http://localhost:8080/api/vouchers"; // Thay port nếu khác

export const vouchersApi = {
  create: async (data: VoucherRequest): Promise<VoucherResponse> => {
    const res = await fetch(API_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    });
    return res.json();
  },

  update: async (id: number, data: VoucherRequest): Promise<VoucherResponse> => {
    const res = await fetch(`${API_URL}/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    });
    return res.json();
  },

  delete: async (id: number): Promise<void> => {
    const res = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    if (!res.ok) throw new Error("Xóa voucher thất bại");
},

  getAll: async (): Promise<VoucherResponse[]> => {
    const res = await fetch(API_URL);
    return res.json();
  },

  searchByCode: async (code: string): Promise<VoucherResponse> => {
    // Lưu ý: RequestParam trong Spring là ?code=...
    const res = await fetch(`${API_URL}/search?code=${code}`);
    if (!res.ok) throw new Error("Voucher not found");
    return res.json();
  },
};
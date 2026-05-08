export interface UsersResponse {
  id: number;
  name: string;
  email: string;
  phone: string;
  createdAt: string;
}

export interface UsersRequest {
  name: string;
  email: string;
  phone: string;
}
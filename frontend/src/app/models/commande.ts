export interface Commande {
  id?: number;
  dateCommande?: string;
  statut: string;
  client?: { id: number };
}
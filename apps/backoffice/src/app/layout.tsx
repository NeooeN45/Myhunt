import type { Metadata } from 'next';
import './globals.css';

export const metadata: Metadata = {
  title: 'CyneTrace Backoffice',
  description:
    "Pilotage des territoires, revue IA et supervision de la synchronisation cynégétique.",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="fr">
      <body>{children}</body>
    </html>
  );
}

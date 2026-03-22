import { HealthController } from './health.controller';

describe('HealthController', () => {
  it('returns a healthy payload', () => {
    const controller = new HealthController();
    const result = controller.getHealth();

    expect(result.service).toBe('cynetrace-api');
    expect(result.status).toBe('ok');
    expect(result.timestamp).toEqual(expect.any(String));
  });
});

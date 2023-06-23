import {RoundingPipe} from "../pipe/rounding.pipe";

describe('RoundingPipe', () => {

  const pipe = new RoundingPipe();

  it('transforms 1.14777777 to 1.15', () => {
    expect(pipe.transform(1.14777777, 2)).toBe(1.15);
  });

  it('transforms 100.463465 to 100.5', () => {
    expect(pipe.transform(100.463465, 1)).toBe(100.5);
  });

});
